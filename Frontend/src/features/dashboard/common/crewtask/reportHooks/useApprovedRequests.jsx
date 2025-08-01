import React from 'react'
import { useState,useEffect } from 'react';
import { requestApi } from '../../../../../services/reportRequest';
import { fetchApprovedRequests } from '../../../../../services/reportRequest';
import useSWR from 'swr';
const useApprovedRequests = () => {
    const [fetchUrl,setFetchUrl]=useState("");
   useEffect(()=>{
    setFetchUrl(`${requestApi}`);
   },[])
   const {data,isLoading,error}=useSWR(fetchUrl,fetchApprovedRequests,requestApi);
  return {data, isLoading,error}
}

export default useApprovedRequests