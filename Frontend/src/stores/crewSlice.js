import {create} from "zustand";
export const taskApi ="http://3.34.130.139:8081/mdt/task/get-task-by-crew-id"
export const createTaskSlice = (set) => ({
  tasks: [],
  totalPages:0,
  currentPage:1,
  isLoading: false,
  error: null,
  fetchTasks: async (id) => {
    set({ isLoading: true });
    try {
      // your fetch logic
      const res = await fetch(`${taskApi}/${id}`);
      const data = await res.json();
      set({ tasks: data, isLoading: false, totalPages:data.totalPages,currentPage:data.currentPage});
    } catch (error) {
      set({ error, isLoading: false });
    }
  },
});
