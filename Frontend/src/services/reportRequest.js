import { useAppStore } from "../stores/store";
import toast from "react-hot-toast";
export const requestApi ="http://3.34.199.155:8081/mdt/report";
const token =useAppStore.getState().token;

export const fetchApprovedRequests=async()=>{
   return fetch(`${requestApi}/get-approved-report-requests-as-crew`,{
    method:"GET",
    headers:{
        "Content-Type":"application/json",
        Authorization: `Bearer ${token}`,
    }
   }).then(res => {
    if (!res.ok) {
      throw new Error("Failed to fetch materials");
    }
    return res.json();
  });
        
    
}