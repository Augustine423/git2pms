import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
const TaskRequest = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    position: "",
    componentName: "",
    taskType: "",
    kind: "P",
    critical: false,
  });
  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === "checkbox" ? checked : value,
    });
  };
  const handleCancel = () => {
    navigate("/dashboard/crew-task");
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://pms.aioceaneye.com:9091/mdt/task/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });
      if (response.ok) {
        navigate("/dashboard/crew-task");
      } else {
        console.error("Failed to save task");
      }
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };
  return (
    <div className="min-h-screen p-6 mx-auto bg-white rounded-[30px] shadow-2xl overflow-hidden">
      <form onSubmit={handleSubmit} className="space-y-6">
        {/* Title */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">Task Title:</label>
          <input
            type="text"
            name="title"
            placeholder="Enter task title"
            value={formData.title}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200"
            required
          />
        </div>
        {/* Position */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">Description:</label>
          <input
            type="text"
            name="description"
            placeholder="Enter description"
            value={formData.description}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200"
            required
          />
        </div>
        {/* Category */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">Position:</label>
          <input
            type="text"
            name="position"
            placeholder="Enter category (e.g., C/O, 1/E, 2/E)"
            value={formData.position}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200"
            required
          />
        </div>
        {/* Task Type */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">ComponentName:</label>
          <input
            type="text"
            name="componentName"
            placeholder="Text"
            value={formData.componentName}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200"
            required
          />
        </div>
        {/* Component Name */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">TaskType:</label>
          <input
            type="text"
            name="taskType"
            placeholder="Enter component name Daily, Weekly, Monthly"
            value={formData.taskType}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200"
            required
          />
        </div>
        {/* Critical Checkbox */}
        <div className="flex items-center gap-4">
          <label className="w-32 font-semibold">Critical:</label>
          <input
            type="checkbox"
            name="critical"
            checked={formData.critical}
            onChange={handleChange}
            className="w-5 h-5"
          />
        </div>
        {/* Description */}
        <div className="flex items-start gap-4">
          <label className="w-32 pt-2 font-semibold">Kind:</label>
          <textarea
            name="kind"
            placeholder="Enter the kind, PMS, CMS, etc."
            rows={4}
            value={formData.kind}
            onChange={handleChange}
            className="flex-1 border rounded-md px-4 py-2 focus:ring focus:ring-blue-200 resize-none"
            required
          />
        </div>
        {/* Buttons */}
        <div className="flex justify-end space-x-4 pt-4">
          <button
            type="submit"
            className="bg-[#1F3C88] text-white font-semibold px-6 py-2 rounded-md shadow-md hover:bg-blue-800 transition"
          >
            Save
          </button>
          <button
            type="button"
            onClick={handleCancel}
            className="border border-[#1F3C88] text-[#1F3C88] font-semibold px-6 py-2 rounded-md shadow-md hover:bg-blue-50 transition"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  );
};
export default TaskRequest;
