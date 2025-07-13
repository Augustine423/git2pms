import {
  Search,
  Plus,
  ChevronDown,
  ChevronRight,
  ExternalLink,
} from "lucide-react";
const tableHeader = "text-white font-semibold text-md bg-[#1f3c88] p-2";
const tableRow = "text-md text-black text-center border-b border-gray-200";
const Sidebar = () => {
  return (
    <>
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-2 mb-4">
        <div className="bg-white rounded-xl shadow-md p-4">
          <div className="flex justify-between items-center mb-2">
            <h3 className="font-bold text-md text-[#1f3c88]">Repair Date</h3>
          </div>
          <table className="w-full text-md">
            <thead>
              <tr className="bg-[#1f3c88] text-white">
                <th className={tableHeader}>No.</th>
                <th className={tableHeader}>Category</th>
                <th className={tableHeader}>Manager</th>
                <th className={tableHeader}>Repair Date</th>
              </tr>
            </thead>
            <tbody>
              {[
                ["1", "Main Engine", "Park", "00-00-0000"],
                ["2", "Rudder", "Kim", "00-00-0000"],
                ["3", "Ballast Tank", "Han", "00-00-0000"],
                ["4", "Valve", "Kim", "00-00-0000"],
                ["5", "Generator Engine", "Song", "00-00-0000"],
              ].map((row, i) => (
                <tr key={i} className={tableRow}>
                  <td className="p-2">{row[0]}</td>
                  <td className="p-2">{row[1]}</td>
                  <td className="p-2">{row[2]}</td>
                  <td className="p-2">{row[3]}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="bg-white rounded-xl shadow-md p-4">
          <div className="flex justify-between items-center mb-2">
            <h3 className="font-bold text-md text-[#1f3c88]">Documents</h3>
          </div>
          <table className="w-full text-md">
            <thead>
              <tr className="bg-[#1f3c88] text-white">
                <th className={tableHeader}>No.</th>
                <th className={tableHeader}>Type</th>
                <th className={tableHeader}>Name</th>
                <th className={tableHeader}>Manager</th>
                <th className={tableHeader}>Date</th>
              </tr>
            </thead>
            <tbody>
              {[
                ["1", "Report", "Daily Report", "Kim", "00/00"],
                ["2", "Request", "Repair(ID-56285282)", "Park", "00/00"],
                ["3", "Request", "Request(ID-57452952)", "Song", "00/00"],
                ["4", "Report", "Emergency Report", "Han", "00/00"],
                ["5", "Report", "Weekly Report", "Lim", "00/00"],
              ].map((row, i) => (
                <tr key={i} className={tableRow}>
                  <td className="p-2">{row[0]}</td>
                  <td className="p-2">{row[1]}</td>
                  <td className="p-2">{row[2]}</td>
                  <td className="p-2">{row[3]}</td>
                  <td className="p-2">{row[4]}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
};
export default Sidebar;
