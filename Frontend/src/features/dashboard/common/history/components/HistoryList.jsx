import React from "react";
import { FaTasks } from "react-icons/fa";
import { FaDotCircle } from "react-icons/fa";
import { ChevronDown, ChevronUp } from "lucide-react";
import { Link } from "react-router-dom";

const HistoryList = ({
  title,
  links,
  handleSelectItem,
  isOpen,
  toggleMenu,
  expandedMenus,
}) => {
  return (
    <div className="w-full">
      <button
        onClick={() => toggleMenu("history")}
        className="w-full flex items-center justify-between text-white p-2 hover:text-black hover:bg-blue-500                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      rounded-md"
      >
        <div className="flex items-center gap-3 text-black">
          <FaTasks size={20} />
          <span>{title}</span>
        </div>
        {expandedMenus.crewTask ? (
          <ChevronUp size={16} className="text-black" />
        ) : (
          <ChevronDown size={16} className="text-black" />
        )}
      </button>
      {isOpen("history") && (
        <div className="mt-1 space-y-1 w-full text-white">
          {links.map((link, index) => (
            <div key={index} className="ml-9 mt-1 space-y-1">
              <Link
                to={link.path}
                className="py-2 px-2 pl-5 text-black hover:text-black focus:bg-blue-500  rounded-md w-full mb-2 flex justify-start items-center"
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
export default HistoryList;
