import { useAppStore } from "../stores/store";
export const taskApi = "http://3.34.199.155:8081/mdt/task/get-task-by-crew-id";
const token =useAppStore.getState().token;
console.log(token)
export const fetchCrewTask=async()=>{
   const response =await fetch(`${taskApi}`,{
    method:"GET",
    headers:{
        "Content-Type":"application/json",
        Authorization: `Bearer ${token}`,
    }
   })
    if (!response.ok) {
      throw new Error("Failed to fetch crew task");
    }
   
  return response.json()
   
  
  };
        
    
