import { useAppStore } from "../stores/store"
import toast from "react-hot-toast";
export const requestApi ="http://3.34.199.155:8081/mdt"
const token =useAppStore.getState().token;
console.log("token",token)
export const fetchMaterials=async()=>{
   return fetch(`${requestApi}/material/for-request`,{
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
export const storeRequest=async(data)=>{
   try {
    const res = await fetch(`${requestApi}/report/create`, {
      method: "POST",
      body: JSON.stringify(data),
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${useAppStore.getState().token}`,
      },
    });

    if (res.status === 201 || res.status === 200) {
      toast.success("Request submitted successfully!");
    } else {
      const errText = await res.text();
      toast.error(`Submission failed: ${errText}`);
    }

    return res;
  } catch (error) {
    console.error("Store request error:", error);
    toast.error("Network or server error.");
    throw error;
  }
}