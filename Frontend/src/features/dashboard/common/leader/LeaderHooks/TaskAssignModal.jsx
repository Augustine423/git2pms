import React from "react";

const dummyTasks = [
  "Check Engine Room",
  "Inspect Safety Equipment",
  "Replace Oil Filters",
  "Fix Navigation Light",
];

const TaskAssignModal = ({ crew, onClose }) => {
  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white w-full max-w-md p-6 rounded-xl shadow-xl">
        <h2 className="text-lg font-semibold mb-4">
          Assign Task to {crew.name}
        </h2>
        <ul className="space-y-2">
          {dummyTasks.map((task, idx) => (
            <li
              key={idx}
              className="border px-4 py-2 rounded hover:bg-gray-100 cursor-pointer"
              onClick={() => {
                alert(`Task "${task}" assigned to ${crew.name}`);
                onClose();
              }}
            >
              {task}
            </li>
          ))}
        </ul>
        <button
          onClick={onClose}
          className="mt-4 w-full bg-gray-200 hover:bg-gray-300 py-2 rounded"
        >
          Cancel
        </button>
      </div>
    </div>
  );
};

export default TaskAssignModal;
