import { useEffect } from "react";
import { useAppStore } from "../../../../../stores/store";

const LeaderCrewData = () => {
  const id = 1; // ← You can dynamically change this ID later if needed

  const { crew, isLoading, error } = useAppStore((state) => ({
    crew: state.crew,
    isLoading: state.isLoading,
    error: state.error,
  }));
 const { fetchCrew } = useAppStore.getState();
  useEffect(() => {
   
    fetchCrew(id);
  }, [fetchCrew,id]);

  return { crew, isLoading, error };
};

export default LeaderCrewData;
