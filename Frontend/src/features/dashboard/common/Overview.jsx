import React from "react";
// import { useState } from "react";
import TopSummary from './OverviewComponents/TopSummary';
import SeamanInfo from "./OverviewComponents/SeamanInfo";
import Sidebar from "./OverviewComponents/Sidebar";



const Overview = () => {
  // const [searchTerm, setSearchTerm] = useState("");
  return (
    <>
    <div className="min-w-screen bg-gray-100 p-6">
      {/* Top Summary Cards */}
      <TopSummary/>
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
       <SeamanInfo/>
        {/* Sidebar */}
        <div className="space-y-6">
          <Sidebar/>
        </div>
      </div>
    </div>
    </>
  );
};

export default Overview;
