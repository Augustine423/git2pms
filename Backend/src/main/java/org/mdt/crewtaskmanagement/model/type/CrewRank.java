package org.mdt.crewtaskmanagement.model.type;

public enum CrewRank {
    // Bridge/Deck Department
    MASTER,
    CHIEF_OFFICER,
    SECOND_OFFICER,
    THIRD_OFFICER,
    DECK_CADET,
    BOSUN,
    AB,                 // Able Seaman
    OS,                 // Ordinary Seaman
    DECK_BOY,           // or Trainee OS
    WATCHMAN,

    // Engine Department
    CHIEF_ENGINEER,
    SECOND_ENGINEER,
    THIRD_ENGINEER,
    FOURTH_ENGINEER,
    JUNIOR_ENGINEER,
    TRAINEE_ENGINEER,
    ETO,
    FITTER,
    OILER,
    MOTORMAN,
    WIPER,

    // Radio
    RADIO_OFFICER,

    // Catering/Support
    CHIEF_COOK,
    SECOND_COOK,
    MESSMAN,
    STEWARD,
    GALLEY_BOY
    //default
    ,OTHER;
}
