import React from "react";
import { useEffect } from "react";
import { useTranslation } from "../../../common/Language";
import { useLocation, Link } from "react-router-dom";
import ToggleHooks from "../../../hooks/ToggleHooks";
import { BsFillGridFill } from "react-icons/bs";
import versionlogo from "../../../assets/common/versionlogo.png";
import ShipList from "../ship/components/ShipList";
import SeamanList from "../seaman/components/SeamanList";
import { Ship } from "lucide-react";
import CrewTaskList from "./crewtask/components/CrewTaskList";

const SidebarDashboard = ({ handleSelectItem, setResetMenus }) => {
  const { t, lang, setLang } = useTranslation();
  console.log("translation", t);
  const loc = useLocation();
  const { isOpen, toggleMenu, expandedMenus, resetMenus } = ToggleHooks();
  useEffect(() => {
    setResetMenus(resetMenus);
  }, [resetMenus]);
  return (
    <>
      {/* Sidebar */}
      <div
        className={`w-1/6 ${
          loc.pathname == "/dashboard" ? "h-screen" : "min-h-full"
        } bg-primary text-white flex flex-col bg-crew-theme `}
      >
        {/* Logo Section */}
        <div className="p-4 border-b border-crew-theme flex mt-auto sticky top-0 z-50 bg-primary">
          <Ship /> {/* Replace with your logo */}
          <p className="text-white font-semibold text-xl ml-2">AIOCEANEYE</p>
        </div>

        {/* Navigation Items */}
        <nav className="flex-1 p-2 sticky">
          <div className="space-y-1">
            {/* Overview */}
            <Link
              to="/dashboard"
              className="w-full flex items-center justify-between p-2 text-white hover:bg-white hover:text-black rounded-md"
              onClick={() => handleSelectItem("Overview")}
            >
              <div className="flex items-center gap-3">
                <BsFillGridFill size={20} />
                <span>{t.dashboardOverview}</span>
              </div>
            </Link>

            <ShipList
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
            />
            <CrewTaskList
              handleSelectItem={handleSelectItem}
              isOpen={isOpen}
              toggleMenu={toggleMenu}
              expandedMenus={expandedMenus}
              title={t.crewTask}
              links={[
                {
                  label: t.crewTask,
                  path: "crew-task",
                },
                { label: t.requestForm, path: "request-form" },
                { label: t.approvedForm, path: "approved-form" },
              ]}
            />
          </div>
        </nav>

        {/* Version */}
        <div className="flex items-center gap-3 px-4 pb-5 text-white">
          <img src={versionlogo} alt="logo" className="w-6 h-6" />
          <span> ver. 0.9.0</span>
        </div>
      </div>
    </>
  );
};

export default SidebarDashboard;
