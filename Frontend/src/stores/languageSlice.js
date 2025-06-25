export const createLanguageSlice=(set)=>({

    currentLanguage: "ENG",
      setLanguage: (lang) => set({ currentLanguage: lang }),
})