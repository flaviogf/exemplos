using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace Demo.WebApi.Migrations
{
    public partial class insert_user_admin : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Users",
                columns: new[] { "Id", "PasswordHash", "Roles", "Username" },
                values: new object[] { new Guid("b41cea9d-476d-45a5-9fe1-9064ad60ec87"), "$2a$11$xVBDYH6Y188UmgxbQSfEj.I0MnaRmPuXT3Y2kB/DV8dAxmiwYf/yi", "user,admin", "admin" });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "Users",
                keyColumn: "Id",
                keyValue: new Guid("b41cea9d-476d-45a5-9fe1-9064ad60ec87"));
        }
    }
}
