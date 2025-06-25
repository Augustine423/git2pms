import React from 'react'
import { useAppStore } from '../stores/store'
import CrewTask from '../features/dashboard/common/crewtask/components/CrewTask';
import RequestForm from '../features/dashboard/common/crewtask/components/TaskRequest/RequestForm';
export const translations =  {
    ENG:{
        dashboardOverview:"Overview",
        shipManagement:"Ship Management",
        shipOverview:"Overview",
        shipRegister:"Register",
        active:"Active",
        inactive:"Inactive",
        seamanInformation:"Seaman Information",
        seamanOverview:"Overview",
        seamanRegister:"Register",
        salary:"Salary",
        crewInformation:"Crew Information",
        crewTask:"Crew Task",
        crewManagement:"Crew Management",
        crewOverview:"Overview",
        crewRegister:"Register",
        requestForm:"Request Form",
        approvedForm:"Approved Request List",
        approvedRequest:"Approved Request",
        rejectedRequest:"Rejected Request",
        rejectedRequestList:"Rejected Request List",
    }
}
export const useTranslation=()=>{
    const lang = useAppStore((state)=>state.currentLanguage);
    const setLang=useAppStore((state)=>state.setLanguage);
    const t= translations[lang]||{};
    return {t,lang,setLang}
}
  