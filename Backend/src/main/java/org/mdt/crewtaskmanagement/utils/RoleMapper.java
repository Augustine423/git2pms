package org.mdt.crewtaskmanagement.utils;

import org.mdt.crewtaskmanagement.exception.ServiceBaseException;
import org.mdt.crewtaskmanagement.model.Role;
import org.mdt.crewtaskmanagement.model.type.CrewRank;

import java.util.List;

public class RoleMapper {

    public static Role.UserRole mapCrewRankToRole(CrewRank rank) {
        if (rank == null) {
            return null;
        }

        return switch (rank) {
            // Officer ranks
            case CHIEF_OFFICER, SECOND_OFFICER, THIRD_OFFICER, DECK_CADET -> Role.UserRole.LEADER;

            case MASTER -> Role.UserRole.LEADER;

            // Engineer ranks
            case CHIEF_ENGINEER, SECOND_ENGINEER -> Role.UserRole.LEADER;

            case  THIRD_ENGINEER, FOURTH_ENGINEER, JUNIOR_ENGINEER, TRAINEE_ENGINEER, ETO -> Role.UserRole.CREW;

            // Catering/crew roles
            case CHIEF_COOK, SECOND_COOK, MESSMAN, STEWARD, GALLEY_BOY -> Role.UserRole.CREW;

            // Deck crew
            case BOSUN, AB, OS, DECK_BOY, WATCHMAN -> Role.UserRole.CREW;

            // Engine room support
            case FITTER, OILER, MOTORMAN, WIPER -> Role.UserRole.CREW;

            // Radio
            case RADIO_OFFICER -> Role.UserRole.OFFICER;

            // Unknown or custom
            case OTHER -> throw new RuntimeException();
        };
    }
}
