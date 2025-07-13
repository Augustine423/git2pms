import React, { useState, useEffect } from "react";
import { FaRegStar, FaTrashAlt, FaCheckCircle, FaClock } from "react-icons/fa";
import ApprovedRequestListHeader from "./ApprovedRequestListHeader";

const ApprovedRequestList = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [requests, setRequests] = useState([
    {
      title: "Approved by Kim For New Assign",
      description: "Fix Your....",
      date: "Jun 17",
      approved: true,
    },
    {
      title: "Approved by Kim For New Assign",
      description: "Fix Your....",
      date: "Jun 5",
      approved: true,
    },
    {
      title: "Request For New Material",
      description: "Fix Main Engine....",
      date: "Jul 4",
      approved: false,
    },
    {
      title: "Request For New Material",
      description: "Fix Main Engine....",
      date: "Jul 1",
      approved: false,
    },
  ]);

  useEffect(() => {
    const newRequest = localStorage.getItem("newRequest");
    if (newRequest) {
      const parsed = JSON.parse(newRequest);
      const requestWithDefaults = {
        title: parsed.title || "No Title",
        description: parsed.description || "No Description",
        date:
          parsed.date ||
          new Date().toLocaleDateString("en-US", {
            month: "short",
            day: "numeric",
          }),
        approved: false,
      };
      setRequests((prev) => [...prev, requestWithDefaults]);
      localStorage.removeItem("newRequest");
    }
  }, []);

  return (
    <div className="min-w-full  p-6">
      <div className="bg-white min-h-screen rounded-[20px] shadow-2xl">
        <ApprovedRequestListHeader />
        {requests
          .filter(
            (item) =>
              item.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
              item.description.toLowerCase().includes(searchTerm.toLowerCase())
          )
          .map((item, idx) => (
            <div
              key={idx}
              className="flex items-center justify-between border-b px-4 py-4 hover:bg-gray-50 transition"
            >
              <div className="flex items-center space-x-4 w-1/4">
                <FaRegStar className="text-gray-500 hover:text-yellow-400 cursor-pointer" />
                <FaTrashAlt className="text-gray-500 hover:text-red-500 cursor-pointer" />
                <span className="font-bold">
                  {item.approved ? "Approved" : "Request"}
                </span>
              </div>
              <div className="flex-1 text-sm px-2">
                <span className="font-semibold">{item.title}</span>
                <span className="text-gray-500"> - {item.description}</span>
              </div>
              <div className="flex items-center space-x-3 w-28 justify-end">
                <span className="text-sm font-bold text-nowrap text-gray-700">
                  {item.date}
                </span>
                {item.approved ? (
                  <FaCheckCircle className="text-green-500 text-lg" />
                ) : (
                  <FaClock className="text-red-500 text-lg" />
                )}
              </div>
            </div>
          ))}
      </div>
    </div>
  );
};

export default ApprovedRequestList;
