import React from "react";
import { useState } from "react";
import { ChevronDown, Search, FileText } from "lucide-react";
const menuItems = [
  "Personal Information",
  "Qualifications",
  "Appointment Details",
  "Preliminary",
  "Military Information",
  "Medical Check Up",
  "Health Situation",
  "Assessment Job",
  "Certificate",
];

const personalInfoData = [
  { label: "Full Name (in Korean)", value: "text" },
  { label: "Full Name (in English)", value: "text" },
  { label: "Date Of Birth", value: "text" },
  { label: "Address", value: "text" },
  { label: "Nationality", value: "text" },
  { label: "Height", value: "text" },
  { label: "Registration Number", value: "text" },
  { label: "Passport", value: "text" },
  { label: "Education", value: "text" },
  { label: "Religion", value: "text" },
];

const employmentData = [
  { label: "Employment Date", value: "00000" },
  { label: "Ship Size", value: "000000000" },
  { label: "Safety Shoe", value: "text" },
  { label: "Mobile Phone", value: "0000000" },
  { label: "Position", value: "text" },
  { label: "Employee Contract Period", value: "00000" },
  { label: "Management Company", value: "text" },
  { label: "Boarding Ship", value: "00000" },
  { label: "Emergency Contact", value: "text" },
];

const SeamanRegister = () => {
  const [selectedSection, setSelectedSection] = useState(
    "Personal Information"
  );
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const renderContent = () => {
    switch (selectedSection) {
      case "Personal Information":
        return (
          <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
            {/* Profile Image */}
            <div className="flex justify-center md:justify-start">
              <div className="w-48 h-48 bg-gray-300 rounded-full"></div>
            </div>

            {/* Information Grid */}
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-x-8 gap-y-4">
                {personalInfoData.map((item, index) => (
                  <div key={index} className="grid grid-cols-2 gap-4">
                    <span className="text-sm text-gray-600">{item.label}</span>
                    <span className="text-sm font-medium">{item.value}</span>
                  </div>
                ))}
              </div>

              <div className="mt-8 grid grid-cols-2 gap-x-8 gap-y-4">
                {employmentData.map((item, index) => (
                  <div key={index} className="grid grid-cols-2 gap-4">
                    <span className="text-sm text-gray-600">{item.label}</span>
                    <span className="text-sm font-medium">{item.value}</span>
                  </div>
                ))}
              </div>
            </div>
          </div>
        );

      case "Qualifications":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Qualifications</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">License Type</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Issue Date</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Expiry Date</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Issuing Authority</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Appointment Details":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Appointment Details</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Appointment Date</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Contract Duration</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Salary</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Military Information":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Military Information</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Service Status</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Service Period</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Rank</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Medical Check Up":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Medical Check Up</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">
                  Last Check Up Date
                </span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Medical Center</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Status</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Health Situation":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Health Situation</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Overall Health</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Allergies</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Medications</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Assessment Job":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Assessment Job</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Assessment Date</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Score</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Assessor</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      case "Certificate":
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">Certificate</h3>
            <div className="space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Certificate Type</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Issue Date</span>
                <span className="text-sm font-medium">text</span>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <span className="text-sm text-gray-600">Valid Until</span>
                <span className="text-sm font-medium">text</span>
              </div>
            </div>
          </div>
        );

      default:
        return (
          <div className="p-8">
            <h3 className="text-lg font-semibold mb-4">{selectedSection}</h3>
            <p className="text-gray-600">
              Content for {selectedSection} will be displayed here.
            </p>
          </div>
        );
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-6xl mx-auto">
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <h1 className="text-xl font-semibold text-gray-900">
            Seamen Details Info
          </h1>
          <div className="flex space-x-2">
            <button className="flex items-center space-x-1 px-3 py-2 bg-white border border-gray-300 rounded-lg text-sm hover:bg-gray-50">
              <Search className="w-4 h-4" />
              <span>Search</span>
            </button>
            <button className="flex items-center space-x-1 px-3 py-2 bg-white border border-gray-300 rounded-lg text-sm hover:bg-gray-50">
              <FileText className="w-4 h-4" />
              <span>Reports</span>
            </button>
          </div>
        </div>

        {/* Navigation Dropdown */}
        <div className="relative mb-6">
          <button
            onClick={() => setIsDropdownOpen(!isDropdownOpen)}
            className="flex items-center space-x-2 px-4 py-2 bg-slate-800 text-white rounded-lg hover:bg-slate-700"
          >
            <span>{selectedSection}</span>
            <ChevronDown
              className={`w-4 h-4 transition-transform ${
                isDropdownOpen ? "rotate-180" : ""
              }`}
            />
          </button>

          {isDropdownOpen && (
            <div className="absolute top-full left-0 mt-1 w-64 bg-white border border-gray-200 rounded-lg shadow-lg z-10">
              {menuItems.map((item, index) => (
                <button
                  key={index}
                  onClick={() => {
                    setSelectedSection(item);
                    setIsDropdownOpen(false);
                  }}
                  className={`w-full text-left px-4 py-2 hover:bg-gray-50 first:rounded-t-lg last:rounded-b-lg ${
                    selectedSection === item
                      ? "bg-blue-50 text-blue-600"
                      : "text-gray-700"
                  }`}
                >
                  {item}
                </button>
              ))}
            </div>
          )}
        </div>

        {/* Content Area */}
        <div className="bg-white rounded-lg shadow-sm p-6">
          {renderContent()}
        </div>
      </div>
    </div>
  );
};

export default SeamanRegister;
