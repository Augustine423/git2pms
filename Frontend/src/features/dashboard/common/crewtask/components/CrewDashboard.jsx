import React from "react";
import { Outlet } from "react-router-dom";
import SidebarDashboard from './../../SidebarDashboard';

const CrewDashboard = () => {
  return (
    <div className="flex">
      <SidebarDashboard role="crew" />
      <div className="flex-1 p-4">
        <Outlet />
      </div>
    </div>
  );
};

export default CrewDashboard;