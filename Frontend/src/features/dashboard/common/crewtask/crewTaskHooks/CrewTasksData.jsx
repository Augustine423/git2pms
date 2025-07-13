import React from 'react'
import { useEffect } from 'react';
import { useAppStore } from '../../../../../stores/store';

const CrewTasksData = () => {
    const id =5;

    const {contents,totalPages,currentPage} = useAppStore(state => state.tasks);
    const isLoading = useAppStore(state => state.isLoading);
    const error = useAppStore(state => state.error);
     const { fetchTasks } = useAppStore.getState();
    useEffect(()=>{
        fetchTasks(id);
    },[fetchTasks,id])
  return{fetchTasks,isLoading,error,contents}
}

export default CrewTasksData