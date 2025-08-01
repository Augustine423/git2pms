import React, { useEffect } from "react";
import { useTranslation } from "../../../common/Language";
import { useLocation, Link } from "react-router-dom";
import ToggleHooks from "../../../hooks/ToggleHooks";
import { BsFillGridFill } from "react-icons/bs";
import versionlogo from "../../../assets/common/versionlogo.png";
import { Ship } from "lucide-react";
import CrewTaskList from "./crewtask/components/CrewTaskList";
import LeaderTaskList from "./leader/Components/LeaderTaskList";
import HistoryList from "./history/components/HistoryList";

import { useAppStore } from "../../../stores/store";

const SidebarDashboard = ({ handleSelectItem, setResetMenus }) => {
  const { t } = useTranslation();
  const role = useAppStore.getState().account;

  console.log("role", role);

  const loc = useLocation();
  const { isOpen, toggleMenu, expandedMenus, resetMenus } = ToggleHooks();

  useEffect(() => {
    setResetMenus(resetMenus);
  }, [resetMenus]);
  const sidebarBg =
    role === "ROLE_LEADER"
      ? "bg-white"
      : role === "ROLE_CREW"
      ? "bg-green-600"
      : "bg-primary";
  return (
    <div
      className={`w-1/6 min-h-full  text-white flex flex-col bg-white `}
    >
      {/* Logo Section */}
      <div className="p-4 border-b border-crew-theme flex mt-auto sticky top-0 z-50">
        <Ship />
        <p className="text-white font-semibold text-xl ml-2">AIOCEANEYE</p>
      </div>

      {/* Navigation Items */}
      <nav className="flex-1 p-2 sticky">
        <div className="space-y-1">
          {/* Overview */}
          <Link
            to="/dashboard"
            className="w-full flex items-center justify-between p-2 text-black hover:bg-blue-500 hover:text-white rounded-md"
            onClick={() => handleSelectItem("Overview")}
          >
            <div className="flex items-center gap-3">
              <BsFillGridFill size={20} />
              <span>{t.dashboardOverview}</span>
            </div>
          </Link>

          {/* Conditional Task Lists */}
          {role === "ROLE_CREW" && (
            <CrewTaskList
              handleSelectItem={handleSelectItem}
              isOpen={isOpen}
              toggleMenu={toggleMenu}
              expandedMenus={expandedMenus}
              title={t.crewTask}
              links={[
                { label: t.crewTask, path: "crew-task" },
                { label: t.requestForm, path: "request-form" },
                { label: t.approvedForm, path: "approved-form" },
                { label: t.emergencyReport, path: "emergency-report" },
              ]}
            />
          )}

          {role === "ROLE_LEADER" && (
            <>
              <LeaderTaskList
                handleSelectItem={handleSelectItem}
                isOpen={isOpen}
                toggleMenu={toggleMenu}
                expandedMenus={expandedMenus}
                title={t.leaderTask}
                links={[
                  { label: t.leaderTask, path: "leader-task" },
                  { label: t.approvalList, path: "approval-list" },
                  { label: t.assignTask, path: "assign-task" },
                  { label: t.document, path: "document" },
                ]}
              />
              <HistoryList
                handleSelectItem={handleSelectItem}
                isOpen={isOpen}
                toggleMenu={toggleMenu}
                expandedMenus={expandedMenus}
                title={t.history}
                links={[
                  { label: t.crewTaskHistory, path: "crew-task-history" },
                  { label: t.taskHistory, path: "task-history" },
                ]}
              />
            </>
          )}
        </div>
      </nav>

      {/* Version */}
      <div className="flex items-center gap-3 px-4 pb-5 text-white">
        <img src={versionlogo} alt="logo" className="w-6 h-6" />
        <span> ver. 0.9.0</span>
      </div>
    </div>
  );
};
export default SidebarDashboard;

{
  /* <ShipList
              handleSelectItem={handleSelectItem}
              isOpen={isOpen}
              toggleMenu={toggleMenu}
              expandedMenus={expandedMenus}
              title={t.shipManagement}
              links={[
                {
                  label: t.shipOverview,
                  path: "ship-overview",
                },
                { label: t.shipRegister, path: "ship-register" },
                { label: t.active, path: "ship-active" },
                { label: t.inactive, path: "ship-inactive" },
              ]}
            />

            <SeamanList
              handleSelectItem={handleSelectItem}
              isOpen={isOpen}
              toggleMenu={toggleMenu}
              expandedMenus={expandedMenus}
              title={t.seamanInformation}
              links={[
                {
                  label: t.seamanOverview,
                  path: "seaman-overview",
                },
                { label: t.salary, path: "seaman-salary" },
                { label: t.seamanRegister, path: "seaman-register" },
              ]}
            /> */
}
