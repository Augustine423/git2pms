import React from "react";
import TopSummary from "./OverviewComponents/TopSummary";
import Sidebar from "./OverviewComponents/Sidebar";
import SeamanInfo from "./OverviewComponents/SeamanInfo";

const Overview = () => {
  return (
     <div className="min-w-full  p-6">
      <div className=" min-h-screen rounded-[20px] shadow-2xl">
      {/* Top Cards Section */}
      <div className=" mb-4 mt-4">
        <TopSummary />
      </div>

      {/* Repair Date Table Section */}
      <div className="mb-4">
        <Sidebar />
      </div>
      {/* Bottom Sections */}
      <div className="mt-4">
        <SeamanInfo />
      </div>
    </div>
    </div>
  );
};

export default Overview;
