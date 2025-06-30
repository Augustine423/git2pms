import React, { useState, useEffect } from "react";
import Pagination from "./../../../../../common/Pagination";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const CrewTask = () => {
  const [data, setData] = useState([]); // Array of tasks
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1); // Store total pages from backend
  const rowsPerPage = 8;
  const navigate = useNavigate();

  // Fetch from backend on mount
useEffect(() => {
  axios
    .get(`http://13.124.92.115:9091/mdt/task/all?page=${currentPage - 1}&size=${rowsPerPage}`)
    .then((res) => {
      console.log("ii", res.data);
      setData(res.data.contents);
      setTotalPages(res.data.totalPages);
    })
    .catch((err) => console.error("Error fetching data:", err));
}, [currentPage]);

  const currentData = data.slice(
    (currentPage - 1) * rowsPerPage,
    currentPage * rowsPerPage
  );

  return (
    <div className="min-h-screen p-6 mx-auto bg-white rounded-[30px] shadow-2xl overflow-hidden">
      {/* Add Task Button */}
      <div className="flex justify-end px-6 pt-6">
        <button
          onClick={() => navigate("/dashboard/task-request")}
          className="px-6 py-2 bg-white border border-blue-500 text-blue-600 font-medium rounded-md shadow hover:bg-blue-50 transition"
        >
          + Add Task
        </button>
      </div>
      {/* Table */}
      <table className="w-full mt-4 text-sm text-center">
        <thead>
          <tr className="border-b-2 border-[#4C567B] text-black font-bold">
            <th className="p-4">No.</th>
            <th className="p-4">Title</th>
            <th className="p-4">Position</th>
            <th className="p-4">Kind</th>
            <th className="p-4">Task Type</th>
            <th className="p-4">Component</th>
            <th className="p-4">Description</th>
            <th className="p-4">Action</th>
          </tr>
        </thead>
        <tbody>
          {currentData.map((row, index) => (
            <tr
              key={row.id}
              className={`${index % 2 === 0 ? "bg-white" : "bg-gray-100"} text-black`}
            >
              <td className="p-4 font-medium">{row.id}.</td>
              <td className="p-4">{row.title}</td>
              <td className="p-4">{row.position}</td>
              <td className="p-4">{row.kind}</td>
              <td className="p-4">{row.taskType}</td>
              <td className="p-4">{row.componentName}</td>
              <td className="p-4">{row.description}</td>
              <td className="p-4">
                <input
                  type="checkbox"
                  className="w-5 h-5 text-green-600 accent-green-500"
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      {/* Pagination */}
      <div className="flex justify-center py-6 space-x-2">
        <Pagination
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={(page) => setCurrentPage(page)}
        />
      </div>
    </div>
  );
};

export default CrewTask;
