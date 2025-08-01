import React from "react";
import SidebarDashboard from './../../SidebarDashboard';
import { Outlet } from "react-router-dom";

const LeaderDashboard = () => {
  return (
    <div className="flex">
      <SidebarDashboard  />
      <div className="flex-1 p-4">
        <Outlet />
      </div>
    </div>
  );
};

export default LeaderDashboard;
