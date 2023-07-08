import React, { createContext, useState } from "react";
import { useLocalStorage } from "../hooks/useLocalStorage";

export const GlobalCurrentKanbanStateContext = createContext();

export const GlobalCurrentKanbanStateProvider = ({ children }) => {
  const [currentKanban] = useLocalStorage({}, "currentKanban");
  const [currentKanbans, setCurrentKanbans] = useState(currentKanban);

  return (
    <GlobalCurrentKanbanStateContext.Provider
      value={[currentKanbans, setCurrentKanbans]}
    >
      {children}
    </GlobalCurrentKanbanStateContext.Provider>
  );
};
