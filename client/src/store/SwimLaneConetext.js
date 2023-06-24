import React, { createContext, useState } from "react";

export const GlobalSwimLaneStateContext = createContext();

export const GlobalSwimLaneStateProvider = ({ children }) => {
  const [currentSwimLane, setCurrentSwimLane] = useState([]);

  return (
    <GlobalSwimLaneStateContext.Provider
      value={[currentSwimLane, setCurrentSwimLane]}
    >
      {children}
    </GlobalSwimLaneStateContext.Provider>
  );
};
