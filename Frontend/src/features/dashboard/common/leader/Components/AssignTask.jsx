import React, { useState } from "react";
import Pagination from "../../../../../common/Pagination";
const AssignTask = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = 2;
  const crewList = new Array(10).fill({
    name: "text",
    sailorCode: "text",
    nationality: "mm",
    position: "text",
    employmentDate: "00-00-0000",
  });

  return (
    <div className="min-w-full rounded-[30px] p-6">
      <div className="bg-white rounded-[30px] shadow-2xl">
        <table className="w-full mt-2lg text-sm text-center">
          <thead>
            <tr className="border-b-4 border-[#4b99bd] text-black font-bold">
              <th className="p-4">Profile</th>
              <th className="p-4">Name</th>
              <th className="p-4">Sailor Code</th>
              <th className="p-4">Nationality</th>
              <th className="p-4">Position</th>
              <th className="p-4">Employment Date</th>
              <th className="p-4"></th>
            </tr>
          </thead>
          <tbody>
            {crewList?.map((crew, index) => (
              <tr key={index} className="hover:bg-gray-100 text-center">
                <td className="p-4">
                  <div className="w-8 h-8 rounded-full bg-gray-300 mx-auto"></div>
                </td>
                <td className="p-4 font-medium">{crew.name}</td>
                <td className="p-4">{crew.sailorCode}</td>
                <td className="p-4">
                  <img
                    src="https://flagcdn.com/w40/mm.png"
                    alt="Myanmar Flag"
                    className="w-6 h-4 mx-auto"
                  />
                </td>
                <td className="p-4">{crew.position}</td>
                <td className="p-4">{crew.employmentDate}</td>
                <td className="p-4">
                  <button className="bg-blue-800 text-white px-4 py-1 rounded shadow hover:bg-blue-700">
                    + Assign
                  </button>
                </td>
              </tr>
            ))}

            {crewList?.length === 0 && (
              <tr>
                <td colSpan="7" className="p-6 text-gray-500 text-center">
                  No crew data available.
                </td>
              </tr>
            )}
          </tbody>
        </table>

        {/* Pagination */}
        <div className="flex justify-center items-center mt-4 p-4">
          <Pagination
            currentPage={currentPage}
            totalPages={totalPages}
            onPageChange={(page) => setCurrentPage(page)}
          />
        </div>
      </div>
    </div>
  );
};

export default AssignTask;
