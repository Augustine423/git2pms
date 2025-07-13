import React from "react";
import { FaRegStar, FaTrashAlt, FaCheckCircle, FaClock } from "react-icons/fa";
import ApprovalListButton from "./ApprovalListButton"; // Adjust the import path as necessary

const requests = [
  {
    id: 1,
    type: "Request",
    message: "Approved by Kim For New Assign - Fix Your...",
    date: "Jun 17",
    status: "Approved",
  },
  {
    id: 2,
    type: "Approved",
    message: "Approved by Kim For New Assign - Fix Your...",
    date: "Jun 5",
    status: "Approved",
  },
  {
    id: 3,
    type: "Request",
    message: "Request For New Material - Fix Main Engine...",
    date: "Jul 4",
    status: "Pending",
  },
  {
    id: 4,
    type: "Request",
    message: "Request For New Pump - Fix Main Engine...",
    date: "Jul 1",
    status: "Pending",
  },
];

const ApprovalList = () => {
  return (
    <div className="min-w-full  p-6">
      <div className="bg-white min-h-screen rounded-[20px] shadow-2xl">
        <ApprovalListButton />
        {requests.map((item) => (
          <div
            key={item.id}
            className="flex items-center justify-between border-b py-4 px-4 hover:bg-gray-50 transition"
          >
            {/* Left icons + type */}
            <div className="flex items-center gap-4 w-1/4">
              <FaRegStar className="text-gray-500 hover:text-yellow-400 cursor-pointer" />
              <FaTrashAlt className="text-gray-500 hover:text-red-500 cursor-pointer" />
              <span className="font-bold">{item.type}</span>
              
            </div>
{/* Message */}
              <div className="flex-1 text-sm px-2">
                <span className="font-semibold">{item.message}</span>
              </div>
            {/* Right: date + icon + buttons */}
            <div className="flex items-center space-x-3 w-15 justify-end">
              <span className="text-sm font-bold text-gray-700">
                {item.date}
              </span>
              {item.status === "Approved" ? (
                <>
                  <FaCheckCircle className="text-green-500 text-lg" />
                  <button className="bg-gray-800 text-white px-3 py-1 text-sm rounded">
                    Approved
                  </button>
                </>
              ) : (
                <>
                  <FaClock className="text-red-500 text-lg" />
                  <button className="border border-blue-600 text-blue-600 px-3 py-1 text-sm rounded hover:bg-blue-50">
                    Approve
                  </button>
                  <button className="border border-gray-400 text-gray-600 px-3 py-1 text-sm rounded hover:bg-gray-100">
                    Cancel
                  </button>
                </>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ApprovalList;
