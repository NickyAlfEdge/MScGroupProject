package com.team.project.service;

import com.team.project.model.GroupPreference;

import java.util.List;

/**
 * @author Kai
 * @date 5/16/2020
 */

public interface GroupPreferenceService {


    List<GroupPreference> selectGroupPreferenceList();

    GroupPreference saveOrUpdate(GroupPreference groupPreference);

    /**
     * automatically group
     */
    void autoAllocation();
}
