import React from 'react'
import footer from "../assets/common/footer.png";

const Footer = () => {
  return (
    <footer className=" flex items-center justify-between px-6 text-white">
      {/* Left side - Logo */}
      <div className="px-4">
          <img src={footer} className="h-20 w-35"/>
        </div>
   
      {/* Right side - Copyright */}
      <div className="flex items-center gap-2 text-white/80 text-sm">
        <span>©</span>
        <span>Copyright Marine-Drone 2025 , All Reserved</span>
      </div>
    </footer>
  );
}

export default Footer