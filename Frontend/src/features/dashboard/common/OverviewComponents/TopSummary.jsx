const cardStyle = "bg-[#1f3c88] text-white p-2 rounded-xl shadow-md items-center";
const TopSummary = () => {
  return (
    <>
    <div className="grid grid-cols-1 lg:grid-cols-4 gap-2 mb-4">
        {[
          "Crew Status",
          "Assign Jobs",
          "Total Crew",
          "Days",
        ].map((label, index) => (
          <div key={index} className={cardStyle}>
            <p className="text-lg font-semibold">{label}</p>
            <p className="text-sm items-center font-bold">00 / 365 <span className="text-xs">person</span></p>
          </div>
        ))}
      </div>
    </>
  );
};

export default TopSummary;
