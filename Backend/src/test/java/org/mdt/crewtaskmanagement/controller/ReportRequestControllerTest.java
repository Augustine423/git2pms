package org.mdt.crewtaskmanagement.controller;

import org.junit.jupiter.api.Test;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.model.type.CrewRank;
import org.mdt.crewtaskmanagement.utils.RoleMapper;
import static org.junit.jupiter.api.Assertions.*;


class ReportRequestControllerTest {
    @Test
    void testOfficerRanks() {
        assertEquals(Role.UserRole.LEADER, RoleMapper.mapCrewRankToRole(CrewRank.CHIEF_OFFICER));
        assertEquals(Role.UserRole.LEADER, RoleMapper.mapCrewRankToRole(CrewRank.SECOND_OFFICER));
        assertEquals(Role.UserRole.LEADER, RoleMapper.mapCrewRankToRole(CrewRank.MASTER));
        assertEquals(Role.UserRole.LEADER, RoleMapper.mapCrewRankToRole(CrewRank.SECOND_ENGINEER));
    }

    @Test
    void testCrewRanks() {
        assertEquals(Role.UserRole.CREW, RoleMapper.mapCrewRankToRole(CrewRank.THIRD_ENGINEER));
        assertEquals(Role.UserRole.CREW, RoleMapper.mapCrewRankToRole(CrewRank.STEWARD));
        assertEquals(Role.UserRole.CREW, RoleMapper.mapCrewRankToRole(CrewRank.BOSUN));
        assertEquals(Role.UserRole.CREW, RoleMapper.mapCrewRankToRole(CrewRank.OILER));
    }

    @Test
    void testOfficerSpecialRole() {
        assertEquals(Role.UserRole.OFFICER, RoleMapper.mapCrewRankToRole(CrewRank.RADIO_OFFICER));
    }

    @Test
    void testNullRank() {
        assertNull(RoleMapper.mapCrewRankToRole(null));
    }

    @Test
    void testOtherThrowsException() {
        assertThrows(RuntimeException.class, () -> RoleMapper.mapCrewRankToRole(CrewRank.OTHER));
    }
}
