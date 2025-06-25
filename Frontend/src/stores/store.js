
import { create } from "zustand";
import { persist } from "zustand/middleware";
import { createSelectedItemSlice } from "./selectedItemSlice";
import { createSeamanSlice } from "./SeamanSlice";
import { createLanguageSlice } from "./languageSlice";
export const useAppStore = create(
  persist(
    (set) => ({
     ...createSelectedItemSlice(set),
     ...createSeamanSlice(set),
     ...createLanguageSlice(set),
    }),
    {
      name: "app-storage", // key for localStorage
    }
  )
);
