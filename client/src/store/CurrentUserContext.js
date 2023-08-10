import React, { createContext, useState } from "react";
export const GlobalCurrentUserStateContext = createContext();

export const CurrentUserStateContextProvider = ({ children }) => {
  const [currentUser, setCurrentUser] = useState(null);

  return (
    <GlobalCurrentUserStateContext.Provider
      value={[currentUser, setCurrentUser]}
    >
      {children}
    </GlobalCurrentUserStateContext.Provider>
  );
};
