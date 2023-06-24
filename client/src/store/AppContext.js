import React, { createContext, useState } from "react";

// Create a new context
export const GlobalKanbanStateContext = createContext();

// Create a provider component
export const GlobalKanbanStateProvider = ({ children }) => {
  const [allKanbans, setAllKanbans] = useState([]);

  return (
    <GlobalKanbanStateContext.Provider value={[allKanbans, setAllKanbans]}>
      {children}
    </GlobalKanbanStateContext.Provider>
  );
};
