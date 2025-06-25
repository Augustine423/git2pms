import React, { useEffect, useState } from "react";
import { useAppStore } from "../../../stores/store";
import { useLocation } from "react-router-dom";
import { useTranslation } from "../../../common/Language";
import { BellDot, UserRoundPenIcon } from "lucide-react";

const TopBar = () => {
  const { t } = useTranslation();
  const location = useLocation();
  const setSelectedItem = useAppStore((state) => state.setSelectedItem);

  const path = {
    "/dashboard": "dashboardOverview",
    "/dashboard/ship-overview": "shipOverview",
    "/dashboard/ship-register": "shipRegister",
    "/dashboard/ship-active": "active",
    "/dashboard/ship-inactive": "inactive",
    "/dashboard/seaman-overview": "seamanOverview",
    "/dashboard/seaman-register": "seamanRegister",
    "/dashboard/seaman-salary": "salary",
    "/dashboard/crew-task": "crewTask",
    "/dashboard/request-form": "requestForm",
    "/dashboard/approved-form": "approvedForm",
  };

useEffect(() => {
  const itemKey = path[location.pathname];
  console.log("item key", itemKey);
  if (itemKey) {
    setSelectedItem(itemKey);
  }
// eslint-disable-next-line react-hooks/exhaustive-deps
}, [location.pathname, setSelectedItem]);

  const selectedItem = useAppStore((state) => state.selectedItem);
  const [showNotifications, setShowNotifications] = useState(false);

  const toggleNotifications = () => {
    setShowNotifications(!showNotifications);
  };

  return (
    <>
      <div className=" flex-1 flex flex-col w-full pl-4">
        <header className=" relative bg-crew-theme py-2 flex items-center justify-between rounded-md px-4 w-full">
          {/* Left Side: Dashboard Text */}
          <ul className="list-disc list-inside text-white">
            <li className="text-white text-lg font-semibold">
              {t[selectedItem] || "_"}
            </li>
          </ul>

          {/* Right Side: Bell and Profile */}
          <div className="flex items-center space-x-4 relative">
            <button
              className="bg-crew-theme p-2 rounded-md hover:bg-[#12293D] text-white"
              onClick={toggleNotifications}
            >
              <BellDot className="h-5 w-5" />
            </button>

            <div className="relative group">
              <button className="flex items-center space-x-2 bg-crew-theme p-2 rounded-md hover:bg-[#12293D] text-white">
                <UserRoundPenIcon className="h-4 w-4" />
                <span>Kim</span>
                <svg
                  className="h-4 w-4"
                  fill="none"
                  stroke="currentColor"
                  viewBox="0 0 24 24"
                >
                  <path
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth={2}
                    d="M19 9l-7 7-7-7"
                  />
                </svg>
              </button>
              {/* Dropdown Menu */}
              <div className="absolute right-0 mt-4 w-full bg-crew-theme rounded-md shadow-lg hidden group-hover:block z-50">
                <a
                  href="/logout"
                  className="block text-white px-2 py-2 text-sm hover:bg-[#0d1f2e]" // Optional hover color
                >
                  Logout
                </a>
              </div>
            </div>
          </div>
        </header>
        {/* Notifications Panel */}
        {showNotifications && (
          <div className="absolute right-8 top-12 mt-8 bg-white rounded-xl shadow-2xl w-[28rem] max-h-[35rem] overflow-y-auto z-10">
            <div className="flex justify-between items-center p-6 border-b border-gray-300">
              <h2 className="font-bold text-xl">Notifications</h2>
              <button
                onClick={toggleNotifications}
                className="text-2xl font-bold text-gray-500 hover:text-red-500"
              >
                &times;
              </button>
            </div>
            <ul className="divide-y divide-gray-200">
              {[
                {
                  type: "request",
                  message:
                    "Sailor ID P35665 submitted a request for Shore Leave.",
                  time: "2 hours ago",
                },
                {
                  type: "approval",
                  message:
                    "Request for Ship Registration ID 123456 has been approved.",
                  time: "5 hours ago",
                },
                {
                  type: "request",
                  message:
                    "Sailor ID P87654 submitted a request for Equipment Maintenance.",
                  time: "12 hours ago",
                },
                {
                  type: "approval",
                  message:
                    "Crew Change Approval for Ship ID 789456 has been confirmed.",
                  time: "1 day ago",
                },
                {
                  type: "info",
                  message:
                    "Sailor ID P35665 will board Ship ID 134354 WOORI SUN in 2 Days.",
                  time: "1 day ago",
                },
                {
                  type: "request",
                  message:
                    "Request for Ship Inspection ID 987654 submitted by Captain Aung.",
                  time: "2 days ago",
                },
                {
                  type: "approval",
                  message:
                    "Medical Certificate for Seaman ID P56789 has been approved.",
                  time: "3 days ago",
                },
                {
                  type: "info",
                  message:
                    "New regulation update on ship safety procedures issued.",
                  time: "4 days ago",
                },
              ].map((item, i) => (
                <li key={i} className="p-6 hover:bg-gray-50">
                  <p className="text-base leading-relaxed">
                    {item.type === "request" && <strong>Request: </strong>}
                    {item.type === "approval" && <strong>Approved: </strong>}
                    {item.type === "info" && <strong>Info: </strong>}
                    {item.message}
                  </p>
                  <small className="text-gray-500 text-sm">{item.time}</small>
                </li>
              ))}
            </ul>
          </div>
        )}
      </div>
    </>
  );
};

export default TopBar;
