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
    navigate("/dashboard/report");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(
        "http://3.34.130.139:8080/mdt/report/register",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        }
      );

      if (response.ok) {
        const result = await response.json();
        console.log("Success:", result);
        navigate("/dashboard/report"); // Redirect after successful save
      } else {
        console.error("Failed to save report");
      }
    } catch (error) {
      console.error("Error submitting report:", error);
    }
  };

  return (
    <div className="min-h-screen w-full mt-4 shadow-2xl overflow-hidden">
      <div className="bg-white mx-4 rounded-[30px] p-6">
        <form onSubmit={handleSubmit} className="space-y-9 p-6">
          <FormGroup
            label="Title"
            name="title"
            value={formData.title}
            onChange={handleChange}
            placeholder="Enter name"
          />
          <FormGroup
            label="Crew-ID"
            name="crewId"
            value={formData.crewId}
            onChange={handleChange}
            placeholder="Enter ID"
          />
          <FormGroup
            label="Description"
            name="description"
            value={formData.description}
            onChange={handleChange}
            placeholder="Enter description"
            isTextArea
          />
          <FormGroup
            label="Category"
            name="category"
            value={formData.category}
            onChange={handleChange}
            placeholder="Enter category"
          />
          <FormGroup
            label="Report Type"
            name="reportType"
            value={formData.reportType}
            onChange={handleChange}
            placeholder="Enter report type"
          />
          <FormGroup
            label="Content"
            name="content"
            value={formData.content}
            onChange={handleChange}
            placeholder="Enter content"
          />
          <FormGroup
            label="Request Date"
            name="requestDate"
            value={formData.requestDate}
            onChange={handleChange}
            type="date"
          />
          <FormGroup
            label="Material List"
            name="materialList"
            value={formData.materialList}
            onChange={handleChange}
            placeholder="Enter content"
          />

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
    </div>
  );
};

const FormGroup = ({
  label,
  name,
  value,
  onChange,
  placeholder,
  isTextArea,
  type = "text",
}) => (
  <div className="flex items-start gap-4">
    <label htmlFor={name} className="w-32 pt-2 font-semibold">
      {label} :
    </label>
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
