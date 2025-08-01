import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react';
import { taskApi } from '../../../../../services/crewTask';
import useSWR from 'swr';
import { fetchCrewTask } from '../../../../../services/crewTask';
const useCrewTasksData = () => {
    const [currentPage, setCurrentPage] = useState(1); // backend is 0-based
    const pageSize = 5;
    const fetchUrl = `${taskApi}?page=${currentPage}&size=${pageSize}`;
   const {data,isLoading,error}=useSWR(fetchUrl,fetchCrewTask)
   console.log("data",data)
   return {tasks: data?.contents || [],
    totalPages: data?.totalPages || 0,
    currentPage,
    setCurrentPage,
    isLoading,
    error,}
}

export default useCrewTasksData