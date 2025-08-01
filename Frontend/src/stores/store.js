
import { create } from "zustand";
import { persist } from "zustand/middleware";

import { createSeamanSlice } from "./SeamanSlice";
import { createLanguageSlice } from "./languageSlice";


export const useAppStore = create(
  persist(
    (set) => ({
      token: null,
      account: {},
       selectedItem: "",
      setAccount: (account) => set({ account }),
      setToken: (token) => set({ token }),
      setSelectedItem: (itemKey) => set({ selectedItem: itemKey }),
      logout: () => set({ account: {}, token: null }),
     ...createSeamanSlice(set),
     ...createLanguageSlice(set),
    
    }),
    {
       name: "account-storage",
      getStorage: () => localStorage,
    }
  )
);
