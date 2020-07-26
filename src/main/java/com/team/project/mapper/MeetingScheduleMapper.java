package com.team.project.mapper;

import com.team.project.dto.ScheduleDTO;
import com.team.project.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MeetingScheduleMapper {
    long countByExample(MeetingScheduleExample example);

    int deleteByExample(MeetingScheduleExample example);

    int deleteByPrimaryKey(Integer meetingId);

    int insert(MeetingSchedule record);

    int insertSelective(MeetingSchedule record);

    List<MeetingSchedule> selectByExample(MeetingScheduleExample example);

    MeetingSchedule selectByPrimaryKey(Integer meetingId);

    int updateByExampleSelective(@Param("record") MeetingSchedule record, @Param("example") MeetingScheduleExample example);

    int updateByExample(@Param("record") MeetingSchedule record, @Param("example") MeetingScheduleExample example);

    int updateByPrimaryKeySelective(MeetingSchedule record);

    int updateByPrimaryKey(MeetingSchedule record);

    List<ScheduleDTO> selectScheduleListByExample(MeetingScheduleExample meetingScheduleExample);

    List<ProjectPreference> selectGroupInProjectPreferenceWithoutSchedule();

    List<Integer> selectGroupWithSameClient(Integer group_id);

    List<Integer> selectGroupWithSameFacilitator(Integer group_id);

/*    List<ScheduleDTO> selectScheduleListByExample(GroupExample groupExample);

    List<ScheduleDTO> selectScheduleListByExample(ProjectExample projectExample);*/
}
