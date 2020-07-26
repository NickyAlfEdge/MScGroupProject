package com.team.project.service;

import com.team.project.dto.Result;
import com.team.project.dto.ScheduleDTO;
import com.team.project.model.MeetingSchedule;

import java.util.List;

/**
 * by HuBo on 12/05/2020
 */
public interface ScheduleService {
    /**
     * search for all the schedules
     * @return
     */
    List<ScheduleDTO> selectScheduleList();

    /**
     * search for a certain group's schedule
     * @param id
     * @return
     */
    List<ScheduleDTO> selectScheduleByGroup( Integer id);

    /**
     * search for facilitator's schedules
     * @param id
     * @return
     */
    List<ScheduleDTO> selectScheduleListByFacilitator( Integer id);

    /**
     * search for client's schedules
     * @param id
     * @return
     */
    List<ScheduleDTO> selectScheduleListByClient( Integer id);

    /**
     *
     * @return
     */
    Result generateSchedules();

    /**
     *
     * @param meetingScheduleList
     * @return
     */
    List<MeetingSchedule> Update(List<MeetingSchedule> meetingScheduleList);



}
