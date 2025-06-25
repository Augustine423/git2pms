import React from 'react';
import { FaTasks } from "react-icons/fa";
import { FaDotCircle } from "react-icons/fa";
import { ChevronDown, ChevronUp } from "lucide-react";
import { Link } from 'react-router-dom';

const CrewTaskList = ({ title, links, handleSelectItem, isOpen, toggleMenu, expandedMenus }) => {
  return (
    <div className="w-full">
      <button
        onClick={() => toggleMenu("crewTask")}
        className="w-full flex items-center justify-between text-white p-2 hover:text-black hover:bg-black rounded-md"
      >
        <div className="flex items-center gap-3 text-white">
          <FaTasks size={20} />
          <span>{title}</span>
        </div>
        {expandedMenus.crewTask ? (
          <ChevronUp size={16} className='text-white' />
        ) : (
          <ChevronDown size={16} className='text-white' />
        )}
      </button>
      {isOpen("crewTask") && (
        <div className="mt-1 space-y-1 w-full text-white">
          {links.map((link, index) => (
            <div key={index} className="ml-9 mt-1 space-y-1">
              <Link
                to={link.path}
                className="py-2 px-2 pl-5 text-white hover:bg-black focus:bg-white focus:text-black rounded-md w-full mb-2 flex justify-start items-center"
                onClick={() => handleSelectItem(link.label, true)}
              >
                <FaDotCircle className="mr-2 text-[6px] opacity-50" />
                {link.label}
              </Link>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default CrewTaskList;