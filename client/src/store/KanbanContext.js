import React, { createContext, useState } from "react";

export const GlobalKanbanStateContext = createContext();

export const GlobalKanbanStateProvider = ({ children }) => {
  const [allKanbans, setAllKanbans] = useState([]);

  return (
    <GlobalKanbanStateContext.Provider value={[allKanbans, setAllKanbans]}>
      {children}
    </GlobalKanbanStateContext.Provider>
  );
};
