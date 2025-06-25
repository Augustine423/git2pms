import React from 'react'
import { FaDotCircle } from "react-icons/fa";
import {ChevronDown, ChevronUp } from "lucide-react";
import { GiCaptainHatProfile } from "react-icons/gi";
import { Link } from 'react-router-dom';
const SeamanList = ({title,
    links,
    handleSelectItem,
    isOpen,
    toggleMenu,
    expandedMenus,}) => {
  return (
    <div className="w-full">
    <button
      onClick={() => toggleMenu("seaman")}
      className="w-full flex items-center justify-between p-2 hover:bg-black rounded-md"
    >
      <div className="flex items-center gap-3 text-white">
        <GiCaptainHatProfile size={20} />
        <span>{title}</span>
      </div>
      {expandedMenus.info ? (
        <ChevronUp size={16} className='text-white' />
      ) : (
        <ChevronDown size={16} className='text-white' />
      )}
    </button>
    {isOpen("seaman") && (
      <div className="mt-1 space-y-1 w-full text-black">
        {/* eslint-disable-next-line react/prop-types*/}
        {links.map((link, index) => (
          <div key={index} className="ml-9 mt-1 space-y-1">
            <Link
              to={link.path}
              className="py-2 px-2 pl-5 text-white hover:bg-black focus:bg-white focus:text-black rounded-md w-full mb-2 flex justify-start items-center"
              onClick={() => handleSelectItem(link.label,true)} // Corrected onClick
            >
              <FaDotCircle className="mr-2 text-[6px] opacity-0.5" />
              {link.label}
            </Link>
          </div>
        ))}
      </div>
    )}
  </div>
  )
}

export default SeamanList