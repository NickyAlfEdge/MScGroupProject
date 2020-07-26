package com.team.project.service.impl;

import com.team.project.dto.Result;
import com.team.project.dto.ScheduleDTO;
import com.team.project.mapper.GroupMapper;
import com.team.project.mapper.MeetingScheduleMapper;
import com.team.project.mapper.ProjectMapper;
import com.team.project.mapper.ProjectPreferenceMapper;
import com.team.project.model.*;
import com.team.project.service.ScheduleService;
import com.team.project.util.ResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

/**
 * by HuBo on 12/05/2020
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private  MeetingScheduleMapper meetingScheduleMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectPreferenceMapper projectPreferenceMapper;

    public List<ScheduleDTO> selectScheduleList(){
        MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
        meetingScheduleExample.setOrderByClause("meeting_schedule.group_id asc");
        List<ScheduleDTO> scheduleDTOS = meetingScheduleMapper.selectScheduleListByExample(meetingScheduleExample);

        return scheduleDTOS;

    }

    public List<ScheduleDTO> selectScheduleByGroup( Integer groupId){
        MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
        meetingScheduleExample.createCriteria().andGroupIdEqualTo(groupId);
        meetingScheduleExample.setOrderByClause("meeting_schedule.group_id asc");
        List<ScheduleDTO> scheduleDTOS = meetingScheduleMapper.selectScheduleListByExample(meetingScheduleExample);

        return scheduleDTOS;
    }

    public List<ScheduleDTO> selectScheduleListByFacilitator( Integer id){
        GroupExample groupExample = new GroupExample();
        groupExample.createCriteria().andFacilitatorIdEqualTo(id);
        List<Group> groups = groupMapper.selectByExample(groupExample);

        List<Integer> groupIds = groups.stream().map(Group::getGroupId).collect(Collectors.toList());
        if (groupIds.size() == 0)
            groupIds.add(-1);
        MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
        meetingScheduleExample.createCriteria().andGroupIdIn(groupIds);
        meetingScheduleExample.setOrderByClause("meeting_schedule.group_id asc");
        List<ScheduleDTO> scheduleDTOList = meetingScheduleMapper.selectScheduleListByExample(meetingScheduleExample);

        return scheduleDTOList;
    }

    public List<ScheduleDTO> selectScheduleListByClient( Integer id){
        ProjectExample projectExample = new ProjectExample();
        projectExample.createCriteria().andClientIdEqualTo(id);
        List<Project> projects = projectMapper.selectByExample(projectExample);

        List<Integer> projectIds = projects.stream().map(Project::getProjectId).collect(Collectors.toList());
        if (projectIds.size() == 0)
            projectIds.add(-1);
        ProjectPreferenceExample projectPreferenceExample = new ProjectPreferenceExample();
        projectPreferenceExample.createCriteria().andProjectIdIn(projectIds);
        List<Group> groups = projectPreferenceMapper.selectGroupByExample(projectPreferenceExample);

        List<Integer> groupIds = groups.stream().map(Group::getGroupId).collect(Collectors.toList());
        if (groupIds.size() == 0)
            groupIds.add(-1);
        MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
        meetingScheduleExample.createCriteria().andGroupIdIn(groupIds);
        meetingScheduleExample.setOrderByClause("meeting_schedule.group_id asc");
        List<ScheduleDTO> scheduleDTOList = meetingScheduleMapper.selectScheduleListByExample(meetingScheduleExample);

        return scheduleDTOList;
    }

    /**
     *
     * @return
     */
    public Result generateSchedules(){

        Date now = new Date();
        Date initialDateC = null;
        Date LatestDateC = null;
        Date initialDateF = null;
        Date LatestDateF = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            initialDateC = sdf.parse("1900-01-01 10:00:00");
            LatestDateC = sdf.parse("1900-01-01 16:00:00");
            initialDateF = sdf.parse("1900-01-01 10:30:00");
            LatestDateF = sdf.parse("1900-01-01 16:30:00");
        }catch (Exception e) {
            e.printStackTrace();
        }


        List<ProjectPreference> projectPreferenceList = meetingScheduleMapper.selectGroupInProjectPreferenceWithoutSchedule();

        for (ProjectPreference projectPreference : projectPreferenceList){

            List<Integer> groupIdClient = meetingScheduleMapper.selectGroupWithSameClient(projectPreference.getGroupId());
            List<Integer> groupIdFacilitator = meetingScheduleMapper.selectGroupWithSameFacilitator(projectPreference.getGroupId());

            List<MeetingSchedule> meetingScheduleListClient = null;
            if (!groupIdClient.isEmpty()) {
                MeetingScheduleExample meetingScheduleExampleClient = new MeetingScheduleExample();
                MeetingScheduleExample.Criteria criteriaClient = meetingScheduleExampleClient.createCriteria();
                criteriaClient.andGroupIdIn(groupIdClient);
                criteriaClient.andMeetingTypeEqualTo(1);
                meetingScheduleListClient = meetingScheduleMapper.selectByExample(meetingScheduleExampleClient);
            }

            List<MeetingSchedule> meetingScheduleListFacilitator = null;
            if (!groupIdFacilitator.isEmpty()) {
                MeetingScheduleExample meetingScheduleExampleFacilitator = new MeetingScheduleExample();
                MeetingScheduleExample.Criteria criteriaFacilitator = meetingScheduleExampleFacilitator.createCriteria();
                criteriaFacilitator.andGroupIdIn(groupIdFacilitator);
                criteriaFacilitator.andMeetingTypeEqualTo(2);
                meetingScheduleListFacilitator = meetingScheduleMapper.selectByExample(meetingScheduleExampleFacilitator);
            }

            if (meetingScheduleListFacilitator == null || meetingScheduleListFacilitator.isEmpty()){
                MeetingSchedule schedule = new MeetingSchedule();
                schedule.setGroupId(projectPreference.getGroupId());
                schedule.setMeetingType(2);
                schedule.setDay(1);
                schedule.setCreatTime(now);
                schedule.setMeetingTime(initialDateF);

                schedule.setWeekNo(1);
                int i1 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(2);
                int i2 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(3);
                int i3 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(4);
                int i4 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(5);
                int i5 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(6);
                int i6 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(7);
                int i7 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(8);
                int i8 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(9);
                int i9 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(11);
                int i11 = meetingScheduleMapper.insertSelective(schedule);

            }
            else {
                Map<Integer, List<MeetingSchedule>> meetingScheduleListFacilitatorPerWeekNo = meetingScheduleListFacilitator.stream().collect(groupingBy(MeetingSchedule::getWeekNo));
                for (List<MeetingSchedule> meetingScheduleList : meetingScheduleListFacilitatorPerWeekNo.values()) {
                    Collections.sort(meetingScheduleList, new SortClassFacilitator());
                    MeetingSchedule scheduleLatest = meetingScheduleList.get(meetingScheduleList.size()-1);

                    MeetingSchedule scheduleC = new MeetingSchedule();
                    scheduleC.setGroupId(projectPreference.getGroupId());
                    scheduleC.setMeetingType(2);
                    scheduleC.setCreatTime(now);

                    if (scheduleLatest.getDay() == 1 && scheduleLatest.getMeetingTime().equals(LatestDateF)){

                        scheduleC.setDay(5);
                        scheduleC.setWeekNo(scheduleLatest.getWeekNo());
                        scheduleC.setMeetingTime(initialDateF);
                        int i = meetingScheduleMapper.insertSelective(scheduleC);
                    }
                    else {
                        scheduleC.setDay(scheduleLatest.getDay());
                        scheduleC.setWeekNo(scheduleLatest.getWeekNo());
                        scheduleC.setMeetingTime(new Date(scheduleLatest .getMeetingTime().getTime()+ 3600000));
                        int i = meetingScheduleMapper.insertSelective(scheduleC);
                    }
                }
            }

            if (meetingScheduleListClient == null || meetingScheduleListClient.isEmpty()){
                MeetingSchedule schedule = new MeetingSchedule();
                schedule.setGroupId(projectPreference.getGroupId());
                schedule.setMeetingType(1);
                schedule.setDay(1);
                schedule.setCreatTime(now);
                schedule.setMeetingTime(initialDateC);

                schedule.setWeekNo(1);
                int i1 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(2);
                int i2 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(3);
                int i3 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(4);
                int i4 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(5);
                int i5 = meetingScheduleMapper.insertSelective(schedule);

                schedule.setWeekNo(9);
                int i9 = meetingScheduleMapper.insertSelective(schedule);

            }
            else {
                Map<Integer, List<MeetingSchedule>> meetingScheduleListClientPerWeekNo = meetingScheduleListClient.stream().collect(groupingBy(MeetingSchedule::getWeekNo));
                for (List<MeetingSchedule> meetingScheduleList : meetingScheduleListClientPerWeekNo.values()) {
                    Collections.sort(meetingScheduleList, new SortClassClient());
                    MeetingSchedule scheduleLatest = meetingScheduleList.get(meetingScheduleList.size()-1);

                    MeetingSchedule scheduleC = new MeetingSchedule();
                    scheduleC.setGroupId(projectPreference.getGroupId());
                    scheduleC.setMeetingType(1);
                    scheduleC.setCreatTime(now);

                    MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
                    MeetingScheduleExample.Criteria criteria = meetingScheduleExample.createCriteria();
                    criteria.andGroupIdEqualTo(projectPreference.getGroupId());
                    criteria.andMeetingTypeEqualTo(2);
                    List<MeetingSchedule> meetingScheduleListForDay= meetingScheduleMapper.selectByExample(meetingScheduleExample);
                    Integer dayFacilitator;
                    if (meetingScheduleListForDay.size() > 0) {
                        dayFacilitator =  meetingScheduleListForDay.get(0).getDay();
                    }
                    else{
                        dayFacilitator =1;
                    }

                    scheduleC.setDay(dayFacilitator);
                    scheduleC.setWeekNo(scheduleLatest.getWeekNo());
                    if (dayFacilitator == scheduleLatest.getDay())
                        scheduleC.setMeetingTime(new Date(scheduleLatest.getMeetingTime().getTime() + 3600000));
                    else
                        scheduleC.setMeetingTime(scheduleLatest.getMeetingTime());

                    int i = meetingScheduleMapper.insertSelective(scheduleC);


                }
            }


        }

        return ResultUtil.success();
    }

    /**
     *
     * @param meetingScheduleList
     * @return
     */
    public List<MeetingSchedule> Update(List<MeetingSchedule> meetingScheduleList) {

        for (MeetingSchedule meetingSchedule : meetingScheduleList) {

            Date now = new Date();
            meetingSchedule.setUpdateTime(now);

            MeetingScheduleExample meetingScheduleExample = new MeetingScheduleExample();
            MeetingScheduleExample.Criteria criteria = meetingScheduleExample.createCriteria();
            criteria.andGroupIdEqualTo(meetingSchedule.getGroupId());
            criteria.andMeetingTypeEqualTo(meetingSchedule.getMeetingType());
            criteria.andWeekNoEqualTo(meetingSchedule.getWeekNo());

            int i = meetingScheduleMapper.updateByExampleSelective(meetingSchedule,meetingScheduleExample);

        }


        return meetingScheduleList;
    }


}


    class SortClassFacilitator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        MeetingSchedule a = (MeetingSchedule) o1;
        MeetingSchedule b = (MeetingSchedule) o2;

        int flag = a.getDay().compareTo(b.getDay());

        if (flag != 0) {
            return flag;
        } else {
            return a.getMeetingTime().compareTo(b.getMeetingTime());
        }

    }
}

    class SortClassClient implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {

        MeetingSchedule a = (MeetingSchedule) o1;
        MeetingSchedule b = (MeetingSchedule) o2;

        int flag = a.getMeetingTime().compareTo(b.getMeetingTime());

        if (flag != 0) {
            return flag;
        } else {
            return a.getDay().compareTo(b.getDay());
        }

    }
}


