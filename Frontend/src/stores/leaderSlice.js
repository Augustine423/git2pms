import { create } from "zustand";

// ✅ Remove "/all" from the base API to make it reusable
export const crewApi = "http://3.34.199.155:8081/mdt/crew";

export const createLeaderSlice = (set) => ({
  crew: [],
  isLoading: false,
  error: null,
  totalPages: 0,
  currentPage: 1,

  fetchCrew: async (id) => {
    set({ isLoading: true, error: null });
    try {
      const url = id ? `${crewApi}/${id}` : `${crewApi}/all`;
      const res = await fetch(url);

      if (!res.ok) {
        throw new Error(`HTTP error! status: ${res.status}`);
      }

      const data = await res.json();
      set({ crew: data, isLoading: false });
    } catch (error) {
      set({ error: error.message || "Fetch failed", isLoading: false });
    }
  },
});

export const useAppStore = create((set) => ({
  ...createLeaderSlice(set),
}));
