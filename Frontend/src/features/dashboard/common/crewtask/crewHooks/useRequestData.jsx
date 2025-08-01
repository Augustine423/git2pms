import React, { useEffect } from 'react'
import { fetchMaterials, requestApi } from '../../../../../services/requestFormData'
import { useState } from 'react';
import useSWR from 'swr';
import { toast } from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { storeRequest } from '../../../../../services/requestFormData';
const useRequestData = () => {
   const [fetchUrl,setFetchUrl]=useState("");
   useEffect(()=>{
    setFetchUrl(`${requestApi}`);
   },[])
   const {data,isLoading,error}=useSWR(fetchUrl,fetchMaterials,requestApi);


    const navigate = useNavigate();

 
  const [materialList, setMaterialList] = useState(false);

  const [count, setCount] = useState({});
  const [activeRows, setActiveRows] = useState([]);
 

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const handlePlus = (id) =>{
    
    setCount((prev) => ({
    ...prev,
    [id]: (prev[id] ?? 1) + 1, 
  }));
  
  };

  const handleMinus = (id) => {
   
    setCount((prev) => ({ ...prev, [id]: Math.max((prev[id] || 1) - 1, 0) }));
   
  };

  const handleViewMaterial = (e) => {
    e.preventDefault();
    setMaterialList(!materialList);
  };
 const handleAddItem = (rowId) =>{
   
  setActiveRows((prev) => ({ ...prev, [rowId]: true }));
  setCount((prev) => ({ ...prev, [rowId]: prev[rowId] ?? 1 }));
};


const onSubmit = async (formData) => {
  const materials = data.contents
    .map((item, i) => {
      const quantity = count[i] ?? 0;
      if (quantity > 0) {
        return {
          name: item.name,
          type: item.type,
          quantity,
        };
      }
      return null;
    })
    .filter(Boolean);

  const payload = { ...formData, materials };
  console.log("submit", payload);

  try {
    const res = await storeRequest(payload);

    if (res.status === 201 || res.status === 200) {
      
      navigate("/dashboard/approval-list");
    } else {
      const text = await res.text(); // optional: show error detail
      
    }
  } catch (err) {
    console.error("Error submitting request:", err);
    
  }
};

   return {data,isLoading,error,register,errors,handleAddItem,handleMinus,
    handlePlus,handleSubmit,handleViewMaterial,onSubmit,materialList,activeRows,count};
}

export default useRequestData