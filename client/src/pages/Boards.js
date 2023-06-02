import { useEffect, useState } from "react";
import axios from "axios";

const Boards = () => {
  const [kanbans, setKanbans] = useState([]);

  useEffect(() => {
    console.log("use effect ran call was made");
    const boards = [];
    axios.get("api/kanban/getAllKanban").then((e) => {
      e.data.forEach((e) => {
        boards.push(e);
      });
      setKanbans(boards);
    });
  }, []);

  return (
    <div>
      {kanbans.map((e) => {
        return <div>{e.title}</div>;
      })}
    </div>
  );
};
export default Boards;
