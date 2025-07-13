import React from 'react'
import { Suspense ,useRef, useState} from "react";
import { useLocation,Outlet } from 'react-router-dom'
import SidebarDashboard from './SidebarDashboard';
import Topbar from './Topbar';
 import PageLoading from '../../../common/PageLoading';
 import Footer from "../../../common/Footer";
 import { BellDot } from 'lucide-react';

 import { useAppStore } from '../../../stores/store';
const DashboardLayout = () => {
    const [isLoading, setIsLoading] = useState(false);
    const resetMenusRef = useRef(null);
    const location =useLocation();
    const isOverview= location.pathname==="/dashboard"
    const setSelectedItem=useAppStore((state)=>state.setSelectedItem);
    const handleSelectItem = (item, isToggleItem=false) => {
    if(!isToggleItem && resetMenusRef.current){
        resetMenusRef.current(); 
       }
  
        setIsLoading(true);
        setTimeout(()=>{
        setIsLoading(false);
        setSelectedItem // Dispatch the action to update selectedItem
      },200)
    }
  return (
    <div className="flex bg-gradient-to-r from-slate-300 to-green-300 ">
    <SidebarDashboard handleSelectItem={handleSelectItem} setResetMenus={fn => resetMenusRef.current = fn}/>
    {/* Main Content */}
    <div className="flex flex-1 flex-col w-5/6 min-h-screen justify-between">
   
      <div className="w-full pt-3">
        <Topbar />
      </div>
    
      <div className="flex flex-col min-h-screen xl:min-h-[700px] w-full">
        <Suspense fallback={<PageLoading/>}>
        <div className="w-full md:w-[720px]  min-w-full mx-auto ">
          <Outlet />
          </div>
        </Suspense>
      </div>
    <div className="w-full lg:px-6 xl:px-0 bottom-0">
     <Footer/>
    </div>
    </div>
  </div>
  )
}

export default DashboardLayout