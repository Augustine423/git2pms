import React, { useState } from "react";
import Pagination from "./../../../../../common/Pagination";

const Document = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const totalPages = 2;
  const reports = [
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Report",
      name: "Daily Report",
      manager: "Kim",
      date: "00-00-0000",
    },
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Report",
      name: "Daily Report",
      manager: "Park",
      date: "00-00-0000",
    },
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Request",
      name: "Material Request",
      manager: "Jeon",
      date: "00-00-0000",
    },
    {
      type: "Report",
      name: "Weekly Report",
      manager: "Han",
      date: "00-00-0000",
    },
  ];
  return (
    <div className="min-w-full rounded-[30px] p-6">
      <div className="bg-white rounded-[30px] shadow-2xl">
        <table className="w-full mt-2lg text-sm  text-center">
          <thead>
            <tr className="border-b-4 border-blue-500 text-black font-bold">
              <th className="p-4">No.</th>
              <th className="p-4">Type</th>
              <th className="p-4">Name</th>
              <th className="p-4">Manager</th>
              <th className="p-4">Date</th>
            </tr>
          </thead>
          <tbody>
            {reports?.map((row, index) => (
              <tr key={index} className="even:bg-gray-100 hover:bg-gray-50">
                <td className="p-4 font-medium">{index + 1}</td>
                <td className="p-4">{row.type}</td>
                <td className="p-4">{row.name}</td>
                <td className="p-4">{row.manager}</td>
                <td className="p-4">{row.date}</td>
              </tr>
            ))}

            {reports?.length === 0 && (
              <tr>
                <td colSpan="5" className="p-6 text-gray-500 text-center">
                  No reports available.
                </td>
              </tr>
            )}
          </tbody>
        </table>

        {/* Pagination */}
        <div className="flex justify-center items-center mt-4 gap-2 p-4">
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

export default Document;
