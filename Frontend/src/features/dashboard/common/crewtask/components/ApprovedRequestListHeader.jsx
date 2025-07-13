// ApprovedRequestListHeader.jsx
import { RotateCcw } from "lucide-react";
import React, { useState } from "react";
import { EllipsisVertical } from "lucide-react";
const ApprovedRequestListHeader = ({
  searchTerm,
  setSearchTerm,
  onRefresh,
}) => {
  const [dropdownOpen, setDropdownOpen] = useState(false);

  const handleFilterClick = (filter) => {
    console.log(`Filter selected: ${filter}`);
    setDropdownOpen(false);
    // Optional: trigger callback here
  };

  return (
    <div className="flex justify-between items-center p-4 border-b">
      <div className="flex items-center space-x-2">
        {/* Refresh Button */}
        <button
          onClick={onRefresh}
          className="p-1 border rounded-md hover:bg-gray-100 transition"
          title="Refresh"
        >
          {/* Default lucide size is 24 ‑ make it 16 */}
          <RotateCcw size={22} className="stroke-[1]" />
        </button>

        {/* Filter / more‑options */}
        <div className="relative inline-block">
          <button
            onClick={() => setDropdownOpen(!dropdownOpen)}
            className="p-1 border rounded-md hover:bg-gray-100 transition"
            title="More options"
          >
            <EllipsisVertical size={22} className="stroke-[1]" />
          </button>
          {dropdownOpen && (
            <div className="absolute left-0 mt-2 w-40 bg-white border rounded shadow z-10">
              <ul className="text-sm text-gray-700">
                {["All", "None", "Starred"].map((filter) => (
                  <li
                    key={filter}
                    className="px-4 py-2 hover:bg-gray-100 cursor-pointer"
                    onClick={() => handleFilterClick(filter)}
                  >
                    {filter}
                  </li>
                ))}
              </ul>
            </div>
          )}
        </div>
      </div>

      {/* Search Bar */}
      <input
        type="text"
        placeholder="Search requests..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        className="border px-4 py-2 rounded-md focus:ring focus:ring-blue-200"
      />
    </div>
  );
};

export default ApprovedRequestListHeader;
