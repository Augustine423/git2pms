import React, { useState, useEffect } from "react";
import Pagination from "./../../../../../common/Pagination";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import CrewTasksData from "../crewTaskHooks/CrewTasksData";
import PageLoading from "../../../../../common/PageLoading";

const CrewTask = () => {
  const {contents,isLoading,error,totalPages}=CrewTasksData();
  const [currentPage, setCurrentPage] = useState(1); 
  const navigate = useNavigate();
 if (isLoading) return <PageLoading/>

 
  return (
    
      <div className="min-w-full rounded-[30px] p-6">
        <div className="bg-white rounded-[30px] shadow-2xl">
          {/* Add Task Button */}
          {/* <div className="flex justify-end px-6 pt-6">
            <button
              onClick={() => navigate("/dashboard/task-request")}
              className="px-6 py-2 bg-white border border-blue-500 text-blue-600 font-medium rounded-md shadow hover:bg-blue-50 transition"
            >
              + Add Task
            </button>
          </div> */}

          {/* Table */}
          <table className="w-full mt-2lg text-sm text-center">
            <thead>
              <tr className="border-b-4 border-[#9FA9A2] text-black font-bold">
                <th className="p-4">No.</th>
                <th className="p-4">Task</th>
                <th className="p-4">Manager</th>
                <th className="p-4">Description</th>
                <th className="p-4 text-nowrap">DeadLine</th>
               
              </tr>
            </thead>
            <tbody>
              {contents?.map((row, index) => (
                <tr
                  key={row.id}
                  className="hover:bg-gray-100"
                >
                  <td className="p-4 font-medium">{index+1}</td>
                  <td className="p-4">{row.taskTitle}</td>
                  <td className="p-4">{row.position}</td>
                 
                  <td className="p-4">{row.taskDescription}</td>
                  <td className="p-4">{row.deadlineDate}</td>
                  <td className="p-4">
                    <input
                      type="checkbox"
                      className="w-5 h-5 text-green-600 accent-green-500"
                    />
                  </td>
                </tr>
              ))}
              {contents?.length === 0 && (
                <tr>
                  <td colSpan="8" className="p-6 text-gray-500">
                    No data available.
                  </td>
                </tr>
              )}
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
      </div>
   
  );
};

export default CrewTask;
