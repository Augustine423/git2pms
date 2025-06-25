import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const ReportRequestForm = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    title: "",
    crewId: "",
    description: "",
    category: "",
    reportType: "",
    content: "",
    requestDate: "",
    materialList: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleCancel = () => {
    navigate("/dashboard/report"); // Change as needed
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitted:", formData);
    // API or submission logic here
  };

  return (
   <div className="min-h-screen p-6 mx-auto bg-white rounded-[30px] shadow-2xl overflow-hidden">
      <form onSubmit={handleSubmit} className="space-y-6">
        {/* Title */}
        <FormGroup label="Title" name="title" value={formData.title} onChange={handleChange} placeholder="Enter name" />

        {/* Crew-ID */}
        <FormGroup label="Crew-ID" name="crewId" value={formData.crewId} onChange={handleChange} placeholder="Enter ID" />

        {/* Description */}
        <FormGroup
          label="Description"
          name="description"
          value={formData.description}
          onChange={handleChange}
          placeholder="Enter description"
          isTextArea
        />

        {/* Category */}
        <FormGroup label="Category" name="category" value={formData.category} onChange={handleChange} placeholder="Enter category" />

        {/* Report Type */}
        <FormGroup label="Report Type" name="reportType" value={formData.reportType} onChange={handleChange} placeholder="Enter report type" />

        {/* Content */}
        <FormGroup label="Content" name="content" value={formData.content} onChange={handleChange} placeholder="Enter content" />

        {/* Request Date */}
        <FormGroup label="Request Date" name="requestDate" value={formData.requestDate} onChange={handleChange} type="date" />

        {/* Material List */}
        <FormGroup label="Material List" name="materialList" value={formData.materialList} onChange={handleChange} placeholder="Enter content" />

        {/* Buttons */}
        <div className="flex justify-end space-x-4 pt-4">
          <button
            type="submit"
            className="bg-[#1f3c88] text-white font-semibold px-6 py-2 rounded-md shadow-md hover:bg-blue-800 transition"
          >
            Request
          </button>
          <button
            type="button"
            onClick={handleCancel}
            className="border border-[#1f3c88] text-[#1f3c88] font-semibold px-6 py-2 rounded-md shadow-md hover:bg-blue-50 transition"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};

const FormGroup = ({ label, name, value, onChange, placeholder, isTextArea, type = "text" }) => (
  <div className="flex items-start gap-4">
    <label htmlFor={name} className="w-32 pt-2 font-semibold">{label} :</label>
    {isTextArea ? (
      <textarea
        id={name}
        name={name}
        rows={4}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        className="flex-1 border rounded-md px-4 py-2 outline-none focus:ring focus:ring-blue-200 resize-none"
      />
    ) : (
      <input
        id={name}
        name={name}
        type={type}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        className="flex-1 border rounded-md px-4 py-2 outline-none focus:ring focus:ring-blue-200"
      />
    )}
  </div>
);

export default ReportRequestForm;
