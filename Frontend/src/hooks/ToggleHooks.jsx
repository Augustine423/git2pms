import React from 'react'
import { useState } from 'react';
const ToggleHooks = () => {
 const [expandedMenus, setExpandedMenus] = useState({
     info: false,
     settings: false,
     logs: false,
   });
 
   const toggleMenu = (menu) => {
     setExpandedMenus((prev) => 
       prev[menu] ? {} : { [menu]: true }
     );
   };
   const isOpen =(menu)=> !!expandedMenus[menu];
   const resetMenus=()=>{
    setExpandedMenus({});
   }

   return {isOpen,toggleMenu,expandedMenus,resetMenus}
}

export default ToggleHooks