import React, { useState } from "react";
import {
  FaRegStar,
  FaTrashAlt,
  FaCheckCircle,
  FaClock,
  FaTimes,
} from "react-icons/fa";

const RequestCard = ({ item, onViewDetails }) => {
  return (
    <div className="flex items-center justify-between border-b py-3 px-4 hover:bg-gray-50 transition">
      {/* Left icons */}
      <div className="flex items-center space-x-4">
        <FaRegStar className="text-gray-500 hover:text-yellow-400 cursor-pointer" />
        <FaTrashAlt className="text-gray-500 hover:text-red-500 cursor-pointer" />
      </div>

      {/* Title & Description */}
      <div
        className="flex-1 truncate text-left px-4 text-sm cursor-pointer"
        onClick={() => onViewDetails(item)}
      >
        <span className="font-semibold">{item.title}</span>
        <span className="text-gray-500"> - {item.description}</span>
      </div>

      {/* Date */}
      <div className="w-16 text-sm text-right text-gray-700">{item.date}</div>

      {/* Right icon */}
      <div className="pl-4">
        {item.approved ? (
          <FaCheckCircle className="text-green-500 text-lg" />
        ) : (
          <FaClock className="text-red-500 text-lg" />
        )}
      </div>
    </div>
  );
};

const ApprovedRequestList = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedItem, setSelectedItem] = useState(null);

  const data = [
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
  ];

  const filteredData = data.filter(
    (item) =>
      item.title.toLowerCase().includes(searchTerm.toLowerCase()) ||
      item.description.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleViewDetails = (item) => {
    setSelectedItem(item); // Ensure the item object is passed correctly
  };

  return (
    <div className="min-h-screen p-6 mx-auto bg-white shadow-2xl overflow-hidden">
      {/* Search Bar */}
      <div className="mb-4 flex justify-end">
        <input
          type="text"
          placeholder="Search requests..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          className="border px-4 py-2 rounded-md focus:ring focus:ring-blue-200"
        />
      </div>

      {/* Request List */}
      <div className="bg-white rounded-lg shadow overflow-hidden">
        {filteredData.map((item, idx) => (
          <RequestCard
            key={idx}
            item={item}
            onViewDetails={handleViewDetails}
          />
        ))}
      </div>

      {/* Modal */}
      {selectedItem && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
          <div className="bg-white p-6 rounded-xl shadow-xl max-w-lg w-full relative">
            <button
              onClick={() => setSelectedItem(null)}
              className="absolute top-3 right-3 text-gray-600 hover:text-black"
            >
              <FaTimes />
            </button>
            <h2 className="text-xl font-bold mb-4">Request Details</h2>
            <p>
              <strong>Title:</strong> {selectedItem.title}
            </p>
            <p>
              <strong>Description:</strong> {selectedItem.description}
            </p>
            <p>
              <strong>Date:</strong> {selectedItem.date}
            </p>
            <p>
              <strong>Status:</strong>{" "}
              {selectedItem.approved ? "✅ Approved" : "⏳ Pending"}
            </p>
          </div>
        </div>
      )}
    </div>
  );
};

export default ApprovedRequestList;
